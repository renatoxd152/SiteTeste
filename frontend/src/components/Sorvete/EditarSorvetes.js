import React, { useState } from "react";
import { useNavigate, useParams } from 'react-router-dom';

const EditarSorvete = () => {
	const { id } = useParams();
	const [ mensagem, setMensagem ] = useState("");
	const [ nome, setNome ] = useState("");
	const [ quantidade, setQuantidade ] = useState("");
	const [ preco, setPreco ] = useState("");
	const navigate = useNavigate();

	function handleNome(e) {
		setNome(e.target.value);
	}

	function handleQuantidade(e) {
		setQuantidade(e.target.value);
	}

	function handlePreco(e) {
		setPreco(e.target.value);
	}

	async function handleEditarSorvete() {
		try {
			const response = await fetch(`http://localhost:3000/sorvetes/${id}`, {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ nome: nome, quantidade: quantidade, preco: preco }),
			});

			const data = await response.json();

			navigate("/sorvetes/listar");

			setMensagem(data.mensagem);
		} catch (error) {
			console.error('Erro ao cadastrar o sorvete!' + error);
		}
	};

	function voltar() {
		navigate("/sorvetes/listar");
	}

	return (
		<div>
			<Barra />
			<h1>Editar Sorvete</h1>
			<form className="container mt-4">
				<span>{mensagem}</span>
				<br />
				<label>Nome do sorvete do sorvete</label>
				<input type="text" className="form-control" value={nome} onChange={handleNome} />
				<br />
				<label>Quantidade</label>
				<input type="number" className="form-control" value={quantidade} onChange={handleQuantidade} />
				<br />
				<label>Preço</label>
				<input type="number" className="form-control" value={preco} onChange={handlePreco} />
				<br />
				<button type="button" className="btn btn-secondary me-2" onClick={voltar}>Voltar</button>
				<button type="button" className="btn btn-primary" onClick={handleEditarSorvete}>Editar Sorvete</button>
			</form>
		</div>
	)
}

export default EditarSorvete;