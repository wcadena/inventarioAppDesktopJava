import axios from 'axios';
import { AUTH_CONFIG } from './auth0-variables'
import EventEmitter from 'eventemitter3'
import router from '../router'
import { store } from '../store/store';



class AuthService {

  constructor() {
    this.login = this.login.bind(this)
    this.setSession = this.setSession.bind(this)
    this.logout = this.logout.bind(this)
    this.isAuthenticated = this.isAuthenticated.bind(this)
    this.authenticated = this.isAuthenticated()
    this.authNotifier = new EventEmitter()
  }

  login(equipo) {
    //https://github.com/wcadena/inventarioAppDesktopJava/blob/63f388fac1830563a51a0c2b2159378c064c273c/src/main/java/utils/ConectarRestfull.java
    axios.defaults.headers.post['Content-Type'] ='application/x-www-form-urlencoded';
    axios.post(AUTH_CONFIG.domain, {
      client_id:  AUTH_CONFIG.clientId,
      client_secret: AUTH_CONFIG.clientSecret,
      grant_type: AUTH_CONFIG.grantType,
      username: AUTH_CONFIG.username,
      password: AUTH_CONFIG.password,
      scope: AUTH_CONFIG.scope,
    })
        .then(response => {
          let rs = response.data;
          let authResult = {
            accessToken : rs.access_token,
            idToken: 5,
            expiresIn: rs.expires_in,
            refreshToken: rs.refresh_token,
            equipo: equipo
          }

          this.access_token = response['data']['access_token'];
          if (authResult && authResult.accessToken && authResult.idToken) {
            this.setSession(authResult)
            router.replace('/default/dashboard/ecommerce')
          }

        })
        .catch(error => {
          if (error) {
            router.replace('/')
            console.log(error)
            alert(`Error: ${error}. Check the console for further details.`)
          }
        });
  }

  handleAuthentication() {
    console.log('Entroaca.....12sai')
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        this.setSession(authResult)
        router.replace('/default/dashboard/ecommerce')
      } else if (err) {
        router.replace('/')
        console.log(err)
        alert(`Error: ${err.error}. Check the console for further details.`)
      }
    })
  }

  setSession(authResult) {
    store.dispatch('signInUserWithAuth0', authResult)
    localStorage.setItem('isUserSigninWithAuth0', true)
    // Set the time that the access token will expire at
    let expiresAt = JSON.stringify(
      authResult.expiresIn * 1000 + new Date().getTime()
    )
    localStorage.setItem('access_token', authResult.accessToken)
    localStorage.setItem('refresh_token', authResult.refreshToken)
    localStorage.setItem('id_token', authResult.idToken)
    localStorage.setItem('expires_in', expiresAt)
    localStorage.setItem('equipo', JSON.stringify(authResult.equipo))
    this.authNotifier.emit('authChange', { authenticated: true })
  }

  logout() {
    store.dispatch('signOutUserFromAuth0')
    // Clear access token and ID token from local storage
    localStorage.removeItem('access_token')
    localStorage.removeItem('refresh_token')
    localStorage.removeItem('id_token')
    localStorage.removeItem('expires_in')
    localStorage.removeItem('equipo')
    this.userProfile = null
    this.authNotifier.emit('authChange', false)
    // navigate to the home route
    router.push('/session/login')
  }

  isAuthenticated() {
    // Check whether the current time is past the
    // access token's expiry time
    let expiresAt = JSON.parse(localStorage.getItem('expires_at'))
    return new Date().getTime() < expiresAt
  }
}

export default AuthService;