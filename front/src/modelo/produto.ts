
export default interface Produto {
    id?: number
    nome: string
    preco: number
}

// export default class Produto {
//     public nome: string
//     public preco: number
//     private compraram: number
//     private racasCompraram: Array<Array<string>>

//     constructor(nome: string, preco: number) {
//         this.nome = nome
//         this.preco = preco
//         this.compraram = 0
//         this.racasCompraram = []
//     }

//     public get getRacasCompraram(): Array<Array<string>> {
//         return this.racasCompraram
//     }

//     public get getCompraram(): number {
//         return this.compraram
//     }

//     public compraramMaisUm() {
//         this.compraram = this.compraram + 1
//     }
// }