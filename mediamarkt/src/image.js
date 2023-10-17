export class PCImage {
    constructor(ArticleNr, EAN, Brand, Description, Processor, RAM, STORAGE, GPU) {
        this.ArticleNR = ArticleNr;
        this.EAN = EAN;
        this.PCBrand = Brand;
        this.Description = Description;
        this.PROCESSOR = Processor;
        this.RAM = RAM;
        this.STORAGE = STORAGE;
        this.GPU = GPU;
    }

    static createSampleImage(ArticleNr, EAN, Brand, Description, Processor, RAM, STORAGE,GPU){
        return new PCImage(
            ArticleNr,
            EAN,
            Brand,
            Description,
            Processor,
            RAM,
            STORAGE,
            GPU
        )
    }

}
