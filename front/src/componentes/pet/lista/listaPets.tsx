import React, { useCallback, useEffect, useState } from "react";
import "./listaPets.css"
import Cliente from "../../../modelo/cliente";
import Pet from "../../../modelo/pet";
import AlterarPet from "../alterar/alterarPet";

export default function ListaPet() {
    const [pets, setPets] = useState<Pet[]>([])
    const [clientes, setClientes] = useState<Cliente[]>([])
    const [cliente, setCliente] = useState<Cliente | undefined>(undefined)
    const [pet, setPet] = useState<Pet | undefined>(undefined)
    const [nomeCliente, setNomeCliente] = useState<string>("")

    const pegarUmPet = useCallback(async (nome: string) => {
        const pet = pets.find(p => p.nome === nome)
        if (pet) {
            try {
                const response = await fetch(`http://localhost:32831/pet/${pet.id}`, {
                    method: "GET",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Access-Control-Allow-Origin': '*'
                    },
                })
                const data = await response.json()
                if (response.status === 302) {
                    setPet(data as Pet)
                } else {
                    alert(data)
                }
            } catch (error) {
                alert((error as Error).message)
            }
        } else {
            alert("Esse pet não existe")
        }
    }, [pets])

    const procurarCliente = () => {
        const cliente = clientes.find(c => c.nome === nomeCliente)
        if (cliente) {
            setCliente(cliente)
            setPets(cliente.pets)
        }
    }

    const tirarPetDeCliente = useCallback(async (pet: Pet) => {
        if (cliente) {
            cliente.pets = cliente.pets.filter(p => p.nome !== pet.nome)
            
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
                    return true
                } else {
                    alert("Erro ao tirar pet do cliente")
                    return false
                }
            } catch (error) {
                alert((error as Error).message)
                console.log((error as Error).message);
                return false
            }
        }
    }, [cliente])

    const excluirPet = useCallback(async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>, nome: string) => {
        e.stopPropagation()
        const pet = pets.find(p => p.nome === nome)

        if (pet && (await tirarPetDeCliente(pet))) {
            try {
                const response = await fetch("http://localhost:32831/pet/excluir", {
                    method: "DELETE",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Access-Control-Allow-Origin': '*'
                    },
                    body: JSON.stringify({
                        "id": pet.id
                    })
                })
                if (response.ok) {
                    alert("Pet exlcuído")
                    setPets(pets.filter(p => p.nome !== nome))
                }
            } catch (error) {
                console.log(error);
                alert((error as Error).message)
            }
        }
    }, [pets, tirarPetDeCliente])

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

    const gerarListaPet = useCallback(() => {
        if (pets.length <= 0) {
            return <></>
        } else {
            let listaPet = pets.map((p, i) =>
                <tr className="linhaTabelaPets" key={i} onClick={() => pegarUmPet(p.nome)}>
                    <td>{p.nome}</td>
                    <td>{p.tipo}</td>
                    <td>{p.genero}</td>
                    <td><button className="botaExcluirPet" onClick={(e) => excluirPet(e, p.nome)}>Excluir</button></td>
                </tr>
            )
            return listaPet
        }
    }, [pets, excluirPet, pegarUmPet])

    useEffect(() => {
        gerarListaPet()
    }, [gerarListaPet])

    useEffect(() => {
        getClientes()
    }, [getClientes])

    return (
        <div className="containerListaPet">
            {pet === undefined ? (
                <>
                    <div className="procurarCliente">
                        <select className="seletorClienteListaPet"
                            onChange={e => setNomeCliente(e.target.value)}
                            value={nomeCliente}>
                            <option value="" disabled>Selecione o dono</option>
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

                        <button className="botaoProcurarCliente" onClick={procurarCliente}>
                            Procurar
                        </button>
                    </div>

                    {cliente !== undefined ? (
                        <div className="petsCadastrados">
                            <table className="tabelaPets">
                                <thead>
                                    <tr className="headerTabelaPets">
                                        <th>Nome</th>
                                        <th>Tipo</th>
                                        <th>Genêro</th>
                                        <th>Excluir</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {gerarListaPet()}
                                </tbody>
                            </table>
                        </div>

                    ) : (
                        <></>
                    )}
                </>
            ) : (
                <>
                    <button className="botaVoltarListagemCliente" onClick={() => setPet(undefined)}>
                        Voltar
                    </button>

                    <AlterarPet pet={pet} />
                </>
            )

            }
        </div>
    )

}