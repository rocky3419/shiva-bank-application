function sendMessage() {
  const input = document.getElementById("user-input");
  const message = input.value.trim();
  if (!message) return;

  const chatBody = document.getElementById("chat-body");

  // Show user message
  chatBody.innerHTML += `<div class="user-msg"><strong>You:</strong> ${message}</div>`;

  // Bot reply logic
  let reply = "Sorry, I didn’t understand that. I'm Janu, your assistant 😊";

  const lowerMsg = message.toLowerCase();
  if (lowerMsg.includes("loan")) {
    reply = "You can apply for loans in our Loan Services section.";
  } else if (lowerMsg.includes("account")) {
    reply = "Instant account opening is available on our home page.";
  } else if (lowerMsg.includes("balance")) {
    reply = "Please log in to Net Banking to check your account balance.";
  } else if (lowerMsg.includes("safe") || lowerMsg.includes("secure")) {
    reply = "Yes, Shiva Bank ensures military-grade encryption and secure servers.";
  } else if (lowerMsg.includes("how") && lowerMsg.includes("apply")) {
    reply = "Visit our Services > Loans page to apply.";
  }

  // Show bot response
  setTimeout(() => {
    chatBody.innerHTML += `<div class="bot-msg"><strong>Janu:</strong> ${reply}</div>`;
    chatBody.scrollTop = chatBody.scrollHeight;
  }, 500);

  input.value = "";
}
