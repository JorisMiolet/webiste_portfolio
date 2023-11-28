export class Laptop{
    constructor(EAN, Barcode, Brand, description) {
        this.EAN = EAN;
        this.Barcode = Barcode;
        this.Description = description;
        this.Brand = Brand
    }
    static createSampleLaptop(EAN, Barcode, Brand, description){
        return new Laptop(EAN, Barcode, Brand, description);
    }
}
