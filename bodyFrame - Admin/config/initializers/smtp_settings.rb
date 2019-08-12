Rails.application.config.action_mailer.delivery_method = :smtp
Rails.application.config.action_mailer.smtp_settings = {
    :address        => ENV['SMTP_ADDRESS'],
    :port           => ENV['SMTP_PORT'],
    :authentication => :login,
    :user_name      => ENV['SMTP_USERNAME'],
    :password       => ENV['SMTP_PASSWORD'],
    :domain         => ENV['SMTP_DOMAIN'],
    :enable_starttls_auto => true
  }
