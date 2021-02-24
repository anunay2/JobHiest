import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HireComponent } from './hire/hire.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ChooseRoleComponent } from './choose-role/choose-role.component';
import { JobSeekerProfileComponent } from './job-seeker-profile/job-seeker-profile.component';
import { AuthGuard } from './auth.guard';
import { ShortlistedComponent } from './shortlisted/shortlisted.component';
import { ResumeComponent } from './resume/resume.component';
import { JobRecruiterProfileComponent } from './job-recruiter-profile/job-recruiter-profile.component';
import { AllPostingsComponent } from './all-postings/all-postings.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'job-seeker', component: JobSeekerProfileComponent},
  {path: 'job-recruiter', component: JobRecruiterProfileComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'job-recruiter/hire', component: HireComponent},
  {path: 'choose', component: ChooseRoleComponent},
  {path: 'job-recruiter/all-postings/shortlisted', component: ShortlistedComponent},
  {path: 'shortlisted/resume', component: ResumeComponent},
  {path: 'job-recruiter/all-postings', component: AllPostingsComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
