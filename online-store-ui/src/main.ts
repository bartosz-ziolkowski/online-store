import {
  APP_INITIALIZER,
  importProvidersFrom,
  provideZoneChangeDetection,
} from '@angular/core';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import {
  provideHttpClient,
  withInterceptorsFromDi,
} from '@angular/common/http';

import { AppComponent } from './app/app.component';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { bootstrapApplication } from '@angular/platform-browser';
import { environment } from './environments/environment';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideNgxStripe } from 'ngx-stripe';
import { provideRouter } from '@angular/router';
import { provideToastr } from 'ngx-toastr';
import { routes } from './app/app.routes';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: environment.keycloak.url,
        realm: environment.keycloak.realm,
        clientId: environment.keycloak.clientId,
      },
      initOptions: {
        onLoad: 'check-sso',
        pkceMethod: 'S256',
        silentCheckSsoRedirectUri:
          window.location.origin + '/silent-verify-sso.html',
      },
      loadUserProfileAtStartUp: false,
    });
}

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(
      PaginationModule.forRoot()
      //  KeycloakAngularModule
    ),
    // KeycloakService,
    // {
    //   provide: APP_INITIALIZER,
    //   useFactory: initializeKeycloak,
    //   multi: true,
    //   deps: [KeycloakService],
    // },
    provideAnimations(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideHttpClient(withInterceptorsFromDi()),
    provideRouter(routes),
    provideNgxStripe(environment.stripe.publicKey),
    provideToastr({
      positionClass: 'toast-bottom-right',
      closeButton: true,
      progressBar: true,
    }),
  ],
});
