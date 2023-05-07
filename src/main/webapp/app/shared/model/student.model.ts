export interface IStudent {
  id?: number;
  firstname?: string | null;
  lastname?: string | null;
  email?: string | null;
  dob?: Date | null;
  course?: string | null;
}

export class Student implements IStudent {
  constructor(
    public id?: number,
    public firstname?: string | null,
    public lastname?: string | null,
    public email?: string | null,
    public dob?: Date | null,
    public course?: string | null
  ) {}
}
