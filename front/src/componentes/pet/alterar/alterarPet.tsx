import { useState } from "react";
import "./alterarPet.css"
import Pet from "../../../modelo/pet";

type props = {
    pet: Pet
}



export default function AlterarPet(props: props) {
    const [nome, setNome] = useState<string>(props.pet.nome)
    const [tamanho, setTamanho] = useState<string>(props.pet.tamanho)

    const mudarValorNome = (e: React.ChangeEvent<HTMLInputElement>) => {
        props.pet.nome = e.target.value
        setNome(e.target.value)
    }

    const mudarValorTamanho = (e: React.ChangeEvent<HTMLSelectElement>) => {
        props.pet.tamanho = e.target.value
        setTamanho(e.target.value)
    }

    const alterarPet = async () => {
        try {
            const response = await fetch("http://localhost:32831/pet/atualizar", {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
                body: JSON.stringify(props.pet)
            })

            if (response.ok) {
                alert("Pet alterado com sucesso")
            } else {
                console.log(await response.json());
                alert("Erro ao alterar Pet")
            }
        } catch (error) {
            console.log(error);
            alert((error as Error).message)
        }
    }

    return (
        <div className="containerInformacoesPet">

            <button className="botaoConfirmarAlterarCliente"
                onClick={() => alterarPet()}>
                CONFIRMAR ALTERAÇÕES
            </button>

            <div className="campoPetEditavel">
                <label>Nome:</label>
                <input type="text" value={nome} onChange={mudarValorNome} />
            </div>

            <div className="campoPetFixo">
                <label>Genêro:</label>
                <p>{props.pet.genero}</p>
            </div>

            <div className="campoPetEditavel">
                <label>Tamanho:</label>
                <select className="selectPetAlterar"
                    onChange={mudarValorTamanho}
                    value={tamanho}>
                    <option value="" disabled>Tamanho</option>
                    <option value="Grande">Grande</option>
                    <option value="Médio">Médio</option>
                    <option value="Pequeno">Pequeno</option>
                </select>
            </div>

            <div className="campoPetFixo">
                <label>Tipo:</label>
                <p>{props.pet.tipo}</p>
            </div>

            <div className="campoPetFixo">
                <label>Raça:</label>
                <p>{props.pet.raca}</p>
            </div>

        </div>
    )
}