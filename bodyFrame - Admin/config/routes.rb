Rails.application.routes.draw do
  # Activity Controller
  post '/Assign_Activity', to: 'activity#assign_activity'
  post '/Get_Activities', to: 'activity#get_activities'
  post '/Complete_Activity', to: 'activity#complete_activity'

  # Message Controller
  post '/Send_Message', to: 'message#send_message'
  post '/Get_Messages', to: 'message#get_messages'
  post '/Read_Message', to: 'message#read_message'

  # Notification Controller
  post '/Send_Notification', to: 'notification#send_notification'
  post '/Get_Notifications', to: 'notification#get_notifications'
  post '/Read_Notification', to: 'notification#read_notification'

  # Payment Controller
  post '/Pay_APP', to: 'payment#pay_app'
  post '/Pay_Trainer', to: 'payment#pay_trainer'
  post '/Get_Payment_History', to: 'payment#get_payment_history'

  # Support Controller
  post '/Send_Support_Ticket', to: 'support#send_support_ticket'
  post '/Get_Support_Ticket', to: 'support#get_support_ticket'
  post '/Update_Support_Ticket', to: 'support#update_support_ticket'

  # User Controller
  post '/Login', to: 'user#login'
  post '/Create_User', to: 'user#create_user'
  post '/Update_User', to: 'user#update_user'
  post '/Get_Profile', to: 'user#get_profile'
  post '/Update_Profile', to: 'user#update_profile'
  post '/Set_Card_Information', to: 'user#set_card_information'
  post '/Get_Card_Information', to: 'user#get_card_information'
  post '/Update_Card_Information', to: 'user#update_card_information'
  post '/Forgot_Password', to: 'user#forgot_password'
  post '/Get_Contact_List', to: 'user#get_contact_list'
  post '/Comp_User', to: 'user#comp_user'
  post '/Delete_User_Account', to: 'user#delete_user_account'

  # Dynamic error pages
  get "/404", to: "application#error_404"
  get "/500", to: "application#error_404"
end
