export interface IUser {
  id?: any;
  login?: string;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  jobRole?: string;
  dateOfBirth?: Date;
  email?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: any[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  password?: string;
}

export class User implements IUser {
  constructor(
    public id?: any,
    public login?: string,
    public firstName?: string,
    public middleName?: string,
    public lastName?: string,
    public jobRole?: string,
    public dateOfBirth?: Date,
    public email?: string,
    public activated?: boolean,
    public langKey?: string,
    public authorities?: any[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public password?: string
  ) {}
}
