# agente-api

Projeto Leitura XML para persistência de agente e regiões

A aplicação possui um controller( ApiController ) e os métodos do mesmo podem ser acessados a partir do swagger abaixo:
http://localhost:8080/swagger-ui.html#/Agente-api

No controller, existem 2 métodos para consulta a partir da sigla da região: consultarPorRegiaoEstruturaXML e consultarPorRegiaoEstruturaTabela.

O consultarPorRegiaoEstruturaXML retorna os registros no formato similar ao XML lido.
Ex.:

[
    {
        "sigla": "N",
        "geracao": [
            "1.707",
            "1.707",
            "1.707",
            "1.707",
            "1.707",
            "1.707",
            "1.707"
        ],
        "compra": [
            "1.141",
            "1.141",
            "1.141",
            "1.141",
            "1.141",
            "1.141",
            "1.141"
        ],
        "precoMedio": [
            "1.760",
            "1.760",
            "1.760",
            "1.760",
            "1.760",
            "1.760",
            "1.760"
        ],
        "agente": {
            "codigo": 1,
            "data": "14/03/2000 00:00:00"
        }
    }
]

Já o método consultarPorRegiaoEstruturaTabela retorna os registros a serem mostrados na tabela no front.
Ex.:
[
    {
        "sigla": "N",
        "geracao": 1.707,
        "compra": 1.141,
        "precoMedio": 1.760,
        "agente": {
            "codigo": 1,
            "data": "14/03/2000 00:00:00"
        }
    },
    {
        "sigla": "N",
        "geracao": 1.707,
        "compra": 1.141,
        "precoMedio": 1.760,
        "agente": {
            "codigo": 1,
            "data": "14/03/2000 00:00:00"
        }
    },
    {
        "sigla": "N",
        "geracao": 1.707,
        "compra": 1.141,
        "precoMedio": 1.760,
        "agente": {
            "codigo": 1,
            "data": "14/03/2000 00:00:00"
        }
    },
    {
        "sigla": "N",
        "geracao": 1.707,
        "compra": 1.141,
        "precoMedio": 1.760,
        "agente": {
            "codigo": 1,
            "data": "14/03/2000 00:00:00"
        }
    },
    {
        "sigla": "N",
        "geracao": 1.707,
        "compra": 1.141,
        "precoMedio": 1.760,
        "agente": {
            "codigo": 1,
            "data": "14/03/2000 00:00:00"
        }
    },
    {
        "sigla": "N",
        "geracao": 1.707,
        "compra": 1.141,
        "precoMedio": 1.760,
        "agente": {
            "codigo": 1,
            "data": "14/03/2000 00:00:00"
        }
    },
    {
        "sigla": "N",
        "geracao": 1.707,
        "compra": 1.141,
        "precoMedio": 1.760,
        "agente": {
            "codigo": 1,
            "data": "14/03/2000 00:00:00"
        }
    }
]

As configurações para acesso ao banco encontram-se no arquivo application.properties.

Segue abaixo os scripts das tabelas utilizadas:

CREATE TABLE `agente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cod_codigo` int DEFAULT NULL,
  `dt_data` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `regiao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `des_sigla` varchar(255) DEFAULT NULL,
  `vl_compra` decimal(18,3) DEFAULT NULL,
  `vl_geracao` decimal(18,3) DEFAULT NULL,
  `vl_preco_medio` decimal(18,3) DEFAULT NULL,
  `id_agente` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ysxbemww97no427ylxyhotxj` (`id_agente`),
  CONSTRAINT `FK8ysxbemww97no427ylxyhotxj` FOREIGN KEY (`id_agente`) REFERENCES `agente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1639 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
