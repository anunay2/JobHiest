import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';

export class Jwtdecode{
    decoded: any;
    emailid: string;
    role: any;

    constructor(private cookie: CookieService){}

    getEmailid(): string{
        if (this.cookie.check('Authorization')){
            this.decoded = jwt_decode(this.cookie.get('Authorization'));
            this.emailid = this.decoded.sub;
            return this.emailid;
        }
        return null;
    }

    getRole(): string{
        if (this.cookie.check('Authorization')){
            this.decoded = jwt_decode(this.cookie.get('Authorization'));
            this.role = this.decoded.aud;
            return this.role;
        }
        return null;
    }
}
