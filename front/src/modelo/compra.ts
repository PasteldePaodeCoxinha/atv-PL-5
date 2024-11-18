import Cliente from "./cliente"
import Empresa from "./empresa"
import Pet from "./pet"
import Produto from "./produto"
import Servico from "./servico"

export default interface Compra {
    id?: number
    cliente: Cliente
    pet: Pet
    tipo: string
    comprado: Produto | Servico
    empresa: Empresa
}