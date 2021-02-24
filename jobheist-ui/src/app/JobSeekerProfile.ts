import { Education } from './Education';
import { Experience } from './Experience';
import { Skill } from './Skill';

export class JobSeekerProfile{
    email: string;
    name: string;
    sex: string;
    dob: string;
    currentLocation: string;
    preferredLocation: string;
    skills: Skill[];
    experiences: Experience[];
    educations: Education[];
    appliedJobId: number[];
  }
