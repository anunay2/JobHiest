
export class Job {
    recruiterEmail: string;
    id: number;
    jobTitle: string;
    jobDescription: string;
    organisation: string;
    orgDescription: string;
    workingLocation: string;
    joiningDate: Date;
    salary: Pair = new Pair();
    candidateProfile: DesiredCandidateProfile =  new DesiredCandidateProfile();
    createdOn: number;
}


export class DesiredCandidateProfile{
    totalExperience: Pair = new Pair();
    domains: string[];
    roles: string[];
    age: Pair = new Pair();
    skill: Skills[];
}

export class Pair{
    minValue: number;
    maxValue: number;
}

export class Skills{
    skill: string;
    proficiencyLevel: string;
}
