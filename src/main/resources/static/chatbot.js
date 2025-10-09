document.addEventListener("DOMContentLoaded", function() {
    const chatBody = document.getElementById('chat-body');
    const userInput = document.getElementById('user-input');
    const sendButton = document.getElementById('send-btn');

    // Function to handle sending a message
    function sendMessage() {
        const userMessage = userInput.value.trim();
        if (userMessage === '') return;

        // Display user's message
        appendMessage(userMessage, 'user-message');
        userInput.value = '';

        // Get bot's response after a short delay
        setTimeout(() => {
            const botMessage = getBotResponse(userMessage);
            appendMessage(botMessage, 'bot-message');
        }, 500);
    }
    
    sendButton.addEventListener('click', sendMessage);
    userInput.addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            sendMessage();
        }
    });

    // Function to add a message to the chat display
    function appendMessage(message, className) {
        const messageDiv = document.createElement('div');
        messageDiv.classList.add('message', className);
        messageDiv.textContent = message;
        chatBody.appendChild(messageDiv);
        chatBody.scrollTop = chatBody.scrollHeight; // Scroll to the bottom
    }

    // Simple rule-based logic for the bot's responses
    function getBotResponse(message) {
        const lowerCaseMessage = message.toLowerCase();

        if (lowerCaseMessage.includes('hello') || lowerCaseMessage.includes('hi')) {
            return 'Hello! Welcome to Shiva Bank. How can I help you?';
        }
        if (lowerCaseMessage.includes('balance')) {
            return 'To check your balance, please log in to your account and visit the dashboard.';
        }
        if (lowerCaseMessage.includes('loan')) {
            return 'We offer Home Loans, Car Loans, and Personal Loans. Please visit our "Services" page for more details.';
        }
        if (lowerCaseMessage.includes('hours') || lowerCaseMessage.includes('open')) {
            return 'Our branches are open from 10 AM to 4 PM, Monday to Friday.';
        }
        if (lowerCaseMessage.includes('thank')) {
            return "You're welcome! Is there anything else I can assist with?";
        }
        return "I'm sorry, I don't understand that. You can ask about balance, loans, or opening hours.";
    }
});