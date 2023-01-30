export class Dto{
    public x!: number;
    public y!: number;
    public type!: string;
    public color!: string;
    public id!: string;
    public text!: string;
    public machineToQueue: string[] = []
    public queueToMachine: string[] = []
    public rootGraph: Dto[] = [];
    public productsNumberInStock: number = 10;
    public numberOfProducts!: number;
}