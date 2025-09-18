package bankingwebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(user.getEmail());
            helper.setSubject("🎉 Welcome to Shiva Bank");

            String htmlContent = "<html>" +
                    "<body style='font-family: Arial, sans-serif; background:#f9f9f9; padding:20px;'>" +
                    "<div style='max-width:600px;margin:auto;background:white;padding:20px;border-radius:10px;" +
                    "box-shadow:0 0 10px rgba(0,0,0,0.1);'>" +

                    "<h2 style='color:#2E86C1;'>Welcome, " + user.getFullName() + " 👋</h2>" +
                    "<p>Thank you for registering with <b>Shiva Bank</b>. Your account has been created successfully ✅.</p>" +

                    "<h3>📌 Your Details:</h3>" +
                    "<ul>" +
                    "<li><b>Username:</b> " + user.getUsername() + "</li>" +
                    "<li><b>Phone:</b> " + user.getPhone() + "</li>" +
                    "<li><b>Date of Birth:</b> " + user.getDob() + "</li>" +
                    "</ul>" +

                    "<a href='https://shivabank.netlify.app/login' " +
                    "style='display:inline-block;margin-top:15px;padding:10px 20px;" +
                    "background:#2E86C1;color:white;text-decoration:none;border-radius:5px;'>Login Now</a>" +

                    "<br><br>" +
                    "<p style='font-size:12px;color:#777;'>If you didn’t request this, please ignore this email.</p>" +
                    "<hr>" +
                    "<p style='font-size:12px;color:#555;'>© 2025 Shiva Bank of India. All rights reserved.</p>" +
                    "</div>" +
                    "</body></html>";

            helper.setText(htmlContent, true); // ✅ true = send as HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("❌ Error while sending email: " + e.getMessage(), e);
        }
    }
}
