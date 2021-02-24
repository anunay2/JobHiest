import { Pipe, PipeTransform } from '@angular/core';
import { Job } from '../job';
import { IncomingJobs } from './incomingjobs';

@Pipe({
    name: 'searchFilter'
})
export class JobsFilterPipe implements PipeTransform{

    hold: Set<number> = new Set();
    arr = [];
    jobsincoming: IncomingJobs[];
    transform(jobs: [], searchTerm: string): any[] {
        if (!jobs || !searchTerm){
            return jobs;
        }

        // // return jobs.filter(job => job.jobName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1
        //                   );
    }

    }
