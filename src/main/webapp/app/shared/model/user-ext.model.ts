import { IUser } from '@/shared/model/user.model';

export interface IUserExt {
  id?: number;
  middleName?: string | null;
  jobRole?: string | null;
  dateOfBirth?: string | null;
  user?: IUser | null;
}

export class UserExt implements IUserExt {
  constructor(
    public id?: number,
    public middleName?: string | null,
    public jobRole?: string | null,
    public dateOfBirth?: string | null,
    public user?: IUser | null
  ) {}
}
