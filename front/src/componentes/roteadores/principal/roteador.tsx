import { useEffect, useState } from "react";
import BarraNavegacao from "../../barraNavegacao";
import RoteadorCliente from "../cliente/roteadorCliente";
import RoteadorPet from "../pet/roteadorPet";
import RoteadorProduto from "../produto/roteadorProduto";
import RoteadorServico from "../servico/roteadorServico";


export default function Roteador() {
    const [tela, setTela] = useState<string>("Clientes")

    useEffect(() => {
        document.body.style.backgroundColor = "#2513EB"
    }, [])

    const selecionarView = (novaTela: string, evento: Event) => {
        evento.preventDefault()
        console.log(novaTela);
        setTela(novaTela)
    }

    let barraNavegacao = <BarraNavegacao
        seletorView={selecionarView}
        botoes={['Clientes', 'Pets', 'Produtos', 'Serviços']}
        titulo=""
    />

    if (tela === 'Clientes') {
        return (
            <>
                {barraNavegacao}
                <RoteadorCliente />
            </>
        )
    } else if (tela === 'Pets') {
        return (
            <>
                {barraNavegacao}
                <RoteadorPet />
            </>
        )
    // } else if (tela === 'Produtos') {
    //     return (
    //         <>
    //             {barraNavegacao}
    //             <RoteadorProduto />
    //         </>
    //     )
    // } else if (tela === 'Serviços') {
    //     return (
    //         <>
    //             {barraNavegacao}
    //             <RoteadorServico />
    //         </>
    //     )
    } else {
        return (<>
            <div>
                <p>ERRO!</p>
            </div>
        </>)
    }

}