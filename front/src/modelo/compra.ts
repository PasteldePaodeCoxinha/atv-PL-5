import Cliente from "./cliente"
import Pet from "./pet"
import Produto from "./produto"
import Servico from "./servico"

export default interface Compra {
    id?: number
    cliente: Cliente
    pet: Pet
    tipo: string
    compradoPro?: Produto
    compradoSer?: Servico
}