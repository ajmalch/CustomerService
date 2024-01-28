# **Customer service project**

**Instructions to start the application**

Application can be started in 3 different security modes
1.  disable (diosable)
2. basic http security (basic)
3. open id connect (oidc)

VM argument auth.mode should be set for appropriate security modes

In addition to that, following VM arguments should be set accordingly
1. dataSourceUrl
2. dataSourceUserName
3. dataSourcePassword

**Additional VM arguments for OIDC mode**
For OIDC security mode, following additional VM arguments are expected
1. oauth.jwkSetUri
2. openIdConnectUrl (used in open API swagger , currently not working)