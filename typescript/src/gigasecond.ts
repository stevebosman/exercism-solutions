export class Gigasecond {
  d: Date;

  constructor(d: Date) {
    this.d = d;
  }
  
  public date() {
    return new Date(this.d.getTime() + 1_000_000_000_000)
  }
}
