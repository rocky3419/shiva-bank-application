// Chatbot logic
document.addEventListener("DOMContentLoaded", function () {
  const input = document.getElementById("user-input");
  const chatBody = document.getElementById("chat-body");

  function sendMessage() {
    const userText = input.value.trim();
    if (userText === "") return;

    chatBody.innerHTML += `<div><strong>You:</strong> ${userText}</div>`;
    const botReply = getBotReply(userText);
    chatBody.innerHTML += `<div><strong>Bot:</strong> ${botReply}</div>`;

    input.value = "";
    chatBody.scrollTop = chatBody.scrollHeight;
  }

  function getBotReply(message) {
    const msg = message.toLowerCase();
    if (msg.includes("loan")) {
      return "We offer personal, home, and gold loans. What would you like to know?";
    } else if (msg.includes("card")) {
      return "We provide various credit cards like Platinum and Gold Rewards. Need help choosing?";
    } else if (msg.includes("hi") || msg.includes("hello")) {
      return "Hello! I'm Shiva AI Assistant. How can I help you today?";
    } else {
      return "I'm still learning! Try asking about loans, credit cards, or services.";
    }
  }

  document.querySelector(".chat-input button").addEventListener("click", sendMessage);
  input.addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
      sendMessage();
    }
  });

  // Accordion
  document.querySelectorAll(".accordion").forEach(button => {
    button.addEventListener("click", () => {
      button.classList.toggle("active");
      const panel = button.nextElementSibling;
      panel.style.display = panel.style.display === "block" ? "none" : "block";
    });
  });
});

// EMI Calculator
function calculateEMI() {
  const amount = parseFloat(document.getElementById("amount").value);
  const rate = parseFloat(document.getElementById("rate").value) / 100 / 12;
  const months = parseInt(document.getElementById("months").value);
  
  if (isNaN(amount) || isNaN(rate) || isNaN(months)) {
    document.getElementById("emi-result").innerText = "Please fill all fields correctly.";
    return;
  }

  const emi = (amount * rate * Math.pow(1 + rate, months)) / (Math.pow(1 + rate, months) - 1);
  document.getElementById("emi-result").innerText = `Monthly EMI: ₹${emi.toFixed(2)}`;
  document.querySelectorAll('.faq-question').forEach(btn => {
    btn.addEventListener('click', () => {
      const item = btn.parentElement;
      const isOpen = item.classList.contains('open');

      // close any open item if you want only one open at a time
      document.querySelectorAll('.faq-item.open').forEach(openItem => {
        openItem.classList.remove('open');
        openItem.querySelector('.faq-question').classList.remove('active');
      });

      if (!isOpen) {
        item.classList.add('open');
        btn.classList.add('active');
      }
    });
  });
}
