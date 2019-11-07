export const AUTH_CONFIG = {
  clientId: 5,
  clientSecret: 'kCRAX3lwQCbOlI0EG4i50ees4WEzYBkTOFmY4wuA',
  domain: 'https://devinventario.ecuatask.com/oauth/token',
  callbackUrl: process.env.NODE_ENV === 'development' ? 'http://localhost:8080/callback' : 'http://vuely.theironnetwork.org/callback',
  apiUrl: 'API_IDENTIFIER',
  grantType: 'password',
  username: 'admin@admin.com',
  password: 'password',
  scope: '*'
}