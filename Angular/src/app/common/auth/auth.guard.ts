import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.verifyLogin(state.url);
  }

  verifyLogin(url: string): boolean {
    if (!this.isLoggedIn()) {
      localStorage.setItem('url', url);
      this.router.navigate(['/login']);
      return false;
    } else if (this.isLoggedIn()) {
      return true;
    }
  }

  public isLoggedIn(): boolean {
    let status = false;
    if (localStorage.getItem('isLoggedIn') == "true") {
      status = true;
    }
    return status;
  }
  
}