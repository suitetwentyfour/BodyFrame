class Mailer < ApplicationMailer
  def reset_password_email(first_name, last_name, email, password)
    @password = password
    @first_name = first_name
    @last_name = last_name
    @email = email
    @subject  = "Body Frame - Reset Password Request"
    mail(to: @email, subject: @subject)
  end
end
