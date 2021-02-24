import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {RegistrationLoginService} from './registration-login.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FooterComponent } from './footer/footer.component';
import { HireComponent } from './hire/hire.component';
import { JobSeekerProfileComponent } from './job-seeker-profile/job-seeker-profile.component';
import { ChooseRoleComponent } from './choose-role/choose-role.component';
import { ProfileNavbarComponent } from './profile-navbar/profile-navbar.component';
import { CookieService } from 'ngx-cookie-service';
import { EditSkillsComponent } from './edit-skills/edit-skills.component';
import { AuthGuard } from './auth.guard';
import { ProficiencySelectComponent } from './proficiency-select/proficiency-select.component';
import { ShortlistedComponent } from './shortlisted/shortlisted.component';
import { ResumeComponent } from './resume/resume.component';
import { ExperienceComponent } from './experience/experience.component';
import { EducationComponent } from './education/education.component';
import { JobRecruiterProfileComponent } from './job-recruiter-profile/job-recruiter-profile.component';
import { NavbarProfileRecruiterComponent } from './navbar-profile-recruiter/navbar-profile-recruiter.component';
import { JobRecruiterProfileService } from './job-recruiter-profile.service';
import { AllPostingsComponent } from './all-postings/all-postings.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { JobsFilterPipe } from './dashboard/search-filter.pipe';
import { TitleCasePipe } from '@angular/common';
import { JobApplyComponent } from './job-apply/job-apply.component';
import { JobPostingDetailsComponent } from './job-posting-details/job-posting-details.component';
import { TruncatePipe } from './truncatepipe.pipe';
import { CandidateDialogComponent } from './candidate-dialog/candidate-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NavbarComponent,
    DashboardComponent,
    FooterComponent,
    HireComponent,
    JobSeekerProfileComponent,
    ChooseRoleComponent,
    ProfileNavbarComponent,
    EditSkillsComponent,
    ProficiencySelectComponent,
    ShortlistedComponent,
    ResumeComponent,
    ExperienceComponent,
    EducationComponent,
    JobRecruiterProfileComponent,
    NavbarProfileRecruiterComponent,
    AllPostingsComponent,
    PageNotFoundComponent,
    JobsFilterPipe,
    JobApplyComponent,
    JobPostingDetailsComponent,
    TruncatePipe,
    CandidateDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule,
    FormsModule
  ],

  providers: [RegistrationLoginService, CookieService, AuthGuard, JobRecruiterProfileService, TitleCasePipe],
  bootstrap: [AppComponent],
  entryComponents: [EditSkillsComponent]
})
export class AppModule { }
