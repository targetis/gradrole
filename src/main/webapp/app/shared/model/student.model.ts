export interface IStudent {
  id?: number;
  name?: string | null;
  email?: string | null;
  dob?: Date | null;
  course?: string | null;
}

export class Student implements IStudent {
  constructor(
    public id?: number,
    public name?: string | null,
    public email?: string | null,
    public dob?: Date | null,
    public course?: string | null
  ) {}
}
