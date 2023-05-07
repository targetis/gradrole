import { IUser } from '@/shared/model/user.model';

export interface IUserextra {
  id?: number;
  middlename?: string | null;
  jobrole?: string | null;
  dob?: Date | null;
  user?: IUser | null;
}

export class Userextra implements IUserextra {
  constructor(
    public id?: number,
    public middlename?: string | null,
    public jobrole?: string | null,
    public dob?: Date | null,
    public user?: IUser | null
  ) {}
}
