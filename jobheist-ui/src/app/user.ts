export enum Role{
    JOBSEEKER, RECRUITER
  }

export class User {
    emailid: String;
    username: String;
    password: String; 

    role: any;
    createdon: number;
}
