class ApplicationMailer < ActionMailer::Base
  default from: ENV['SUPPORT_EMAI']
  layout 'mailer'
end
