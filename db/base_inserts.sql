insert into tb_clientes(id, nome, identificacao_federal, registro, tipo, situacao)
values
    (default, 'CARLOS MOREIRA DUARTE', '82141512484','952584295','CPF',true),
    (default, 'MARCIA MARQUES OLIVEIRA', '10242341411','201164987','CPF',true),
    (default, 'ADRIANA BARBOSA ALMEIDA', '50213107350','270534856','CPF',true),
    (default, 'Floricultura Premiere', '14403389000120','615456692543','CNPJ',true),
    (default, 'Posto do Barão', '35876391000197','915087874042','CNPJ',true),
    (default, 'Auto peças Agente', '52044225000143','478694404257','CNPJ',true)
;

insert into tb_telefones(id, pais, ddd, numero, pricipal, id_cliente)
values
    (default,'+55','011','200512922', true,(select id from tb_clientes where identificacao_federal = '82141512484')),
    (default,'+55','011','542069040', false,(select id from tb_clientes where identificacao_federal = '82141512484')),
    (default,'+55','011','142366605', false,(select id from tb_clientes where identificacao_federal = '82141512484')),
    (default,'+55','011','759041980', true,(select id from tb_clientes where identificacao_federal = '10242341411')),
    (default,'+55','011','892192321', false,(select id from tb_clientes where identificacao_federal = '10242341411')),
    (default,'+55','011','267209619', false,(select id from tb_clientes where identificacao_federal = '10242341411')),
    (default,'+55','011','153249121', true,(select id from tb_clientes where identificacao_federal = '50213107350')),
    (default,'+55','011','696990929', true,(select id from tb_clientes where identificacao_federal = '14403389000120')),
    (default,'+55','011','251862727', true,(select id from tb_clientes where identificacao_federal = '35876391000197')),
    (default,'+55','011','728244760', true,(select id from tb_clientes where identificacao_federal = '52044225000143')),
    (default,'+55','011','224921091', false,(select id from tb_clientes where identificacao_federal = '52044225000143'))
;