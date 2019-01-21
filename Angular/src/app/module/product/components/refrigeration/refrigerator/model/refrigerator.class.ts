import { Product } from "../../../../model/product.class";

export class Refrigerator extends Product {

    size: string
    weight: number
    maxVolumetric: number
    freezerVolumetric: number
    iceCubesVolumetric: number
    coolingTechnology: string

}