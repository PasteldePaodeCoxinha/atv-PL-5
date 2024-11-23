/* eslint-disable jsx-a11y/anchor-is-valid */
import { useCallback, useEffect, useState } from "react";
import Cliente from "../../../modelo/cliente";
import "./formularioCadastroPet.css"
import Pet from "../../../modelo/pet";

export default function FormularioCadastroPet() {
    const [nome, setNome] = useState<string>("")
    const [tipo, setTipo] = useState<string>("")
    const [raca, setRaca] = useState<string>("")
    const [genero, setGenero] = useState<string>("")
    const [tamanho, setTamanho] = useState<string>("")
    const [dono, setDono] = useState<string>("")
    const [clientes, setClientes] = useState<Cliente[]>([]);

    const mudarValorNome = (e: React.ChangeEvent<HTMLInputElement>) => {
        setNome(e.target.value)
    }

    const mudarValorTipo = (e: React.ChangeEvent<HTMLInputElement>) => {
        setTipo(e.target.value)
    }

    const mudarValorRaca = (e: React.ChangeEvent<HTMLInputElement>) => {
        setRaca(e.target.value)
    }

    const mudarValorGenero = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setGenero(e.target.value)
    }

    const mudarValorTamanho = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setTamanho(e.target.value)
    }

    const mudarValorDono = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setDono(e.target.value)
    }

    const getClientes = useCallback(async () => {
        try {
            const response = await fetch("http://localhost:32831/cliente/clientes", {
                method: "GET",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
            })

            const data = await response.json()

            if (response.status === 302) {
                setClientes(data)
            } else {
                alert(data)
            }

        } catch (error) {
            alert((error as Error).message)
        }
    }, [])

    const adicionarPetCliente = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault()

        // let cachorroTemDono = false

        // if (cachorroTemDono) {
        //     return
        // }

        const cliente = clientes.find(c => c.nome === dono)

        if (!cliente) {
            alert("Esse dono não existe")
            return
        }

        const pet: Pet = { nome: nome, tipo: tipo, raca: raca, genero: genero, tamanho: tamanho }

        cliente.pets.push(pet)

        try {
            const response = await fetch("http://localhost:32831/cliente/atualizar", {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
                body: JSON.stringify(cliente)
            })
            if (response.ok) {
                alert("Pet cadastrado")
                setNome("")
                setTipo("")
                setRaca("")
                setGenero("")
                setTamanho("")
                setDono("")
            } else {
                console.log("Erro ao cadastrar");
            }

        } catch (error) {
            alert((error as Error).message)
            console.log((error as Error).message);
            return
        }
    }

    useEffect(() => {
        getClientes()
    }, [getClientes])

    return (
        <div className="containerFormularioPet">
            <form className="formularioPet" onSubmit={adicionarPetCliente}>

                <div className="linhaFormularioCadastroPet">

                    <select className="selectPetForms"
                        onChange={mudarValorDono}
                        value={dono}>
                        <option value="" disabled>Dono</option>
                        {clientes.map((c, i) => {
                            return (
                                <option
                                    value={c.nome}
                                    key={i}>
                                    {c.nome}
                                </option>
                            )
                        })}
                    </select>

                </div>

                <div className="linhaFormularioCadastroPet">

                    <input type="text"
                        className="inputPetForms"
                        placeholder="Nome"
                        value={nome}
                        onChange={mudarValorNome}
                        required
                    />

                    <select className="selectPetForms"
                        onChange={mudarValorGenero}
                        value={genero}>
                        <option value="" disabled>Genêro</option>
                        <option value="feminino">Feminino</option>
                        <option value="masculino">Masculino</option>
                    </select>

                </div>


                <div className="linhaFormularioCadastroPet">

                    <input type="text"
                        className="inputPetForms"
                        placeholder="Tipo"
                        value={tipo}
                        onChange={mudarValorTipo}
                        required
                    />

                    <input type="text"
                        className="inputPetForms"
                        placeholder="Raça"
                        value={raca}
                        onChange={mudarValorRaca}
                        required
                    />

                    <select className="selectPetForms"
                        onChange={mudarValorTamanho}
                        value={tamanho}>
                        <option value="" disabled>Tamanho</option>
                        <option value="Grande">Grande</option>
                        <option value="Médio">Médio</option>
                        <option value="Pequeno">Pequeno</option>
                    </select>

                </div>

                <div className="containerBotaoCadastrarPet">
                    <button className="botaoCadastrarPet">CADASTRAR</button>
                </div>
            </form>

        </div>
    )

}