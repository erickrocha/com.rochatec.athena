INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('AC','Acre');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('AL','Alagoas');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('AM','Amazonas');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('AP','Amapá¡');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('BA','Bahia');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('CE','Ceará¡');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('DF','Distrito Federal');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('ES','Espírito Santo');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('GO','Goiás');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('MA','Maranhão');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('MG','Minas Gerais');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('MS','Mato Grosso do Sul');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('MT','Mato Grosso');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('PA','Pará');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('PB','Paraíba');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('PE','Pernambuco');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('PI','Piauí');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('PR','Paraná');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('RJ','Rio de Janeiro');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('RN','Rio Grande do Norte');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('RO','Rondônia');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('RR','Roraima');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('RS','Rio Grande do Sul');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('SC','Santa Catarina');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('SE','Sergipe');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('SP','São Paulo');
INSERT INTO PROVINCE (ACRONYM,NAME) VALUES ('TO','Tocantins');

INSERT INTO ICMS (ID,DESCRIPTION,PERCENTAGE) values (1,'07,00',7);
INSERT INTO ICMS (ID,DESCRIPTION,PERCENTAGE) values (2,'12,00',12);
INSERT INTO ICMS (ID,DESCRIPTION,PERCENTAGE) values (3,'18,00',18);
INSERT INTO ICMS (ID,DESCRIPTION,PERCENTAGE) values (4,'25,00',25);
INSERT INTO ICMS (ID,DESCRIPTION,PERCENTAGE) values (5,'Isento',0);
INSERT INTO ICMS (ID,DESCRIPTION,PERCENTAGE) values (6,'Substituido',0);
INSERT INTO ICMS (ID,DESCRIPTION,PERCENTAGE) values (7,'Não Incidente',0);

INSERT INTO ROLE VALUES ('CUS01','CUSTOMER','Clientes');
INSERT INTO ROLE VALUES ('EMP01','EMPLOYEE','Colaborador');
INSERT INTO ROLE VALUES ('JOB01','JOB','Cargo');
INSERT INTO ROLE VALUES ('CAT01','CATEGORY','Categoria');
INSERT INTO ROLE VALUES ('PRO01','PRODUCT','Produto');
INSERT INTO ROLE VALUES ('NCM01','NCM','NCM');
INSERT INTO ROLE VALUES ('SUP01','SUPPLIER','Fornecedor');
INSERT INTO ROLE VALUES ('NOP01','NATURE_OF_OPERATION','Natureza da Operação');
INSERT INTO ROLE VALUES ('SHI01','SHIPPER','Transportadora');
INSERT INTO ROLE VALUES ('PRE01','PREFERENCE','Preferências');
INSERT INTO ROLE VALUES ('USER1','USER','User');
INSERT INTO ROLE VALUES ('PRF01','PROFILE','Perfil');
INSERT INTO ROLE VALUES ('ADM01','MANAGER_PRICE','Administração de Preços');
INSERT INTO ROLE VALUES ('ALL','CLOSE','Fechar');
INSERT INTO ROLE VALUES ('CAR01','SHIPPER','Transportadora');
INSERT INTO ROLE VALUES ('IIP01','INVOICE_INPUT','Nota Fiscal Entrada');
INSERT INTO ROLE VALUES ('PSO01','PURCHASE_ORDER','Pedidos');
INSERT INTO ROLE VALUES ('PRS01','PRODUCT_SET','Conjuntos');
INSERT INTO ROLE VALUES ('SAL01','SALE_ORDER','Pedido de Venda');

INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('20082010','ABACAXIS PREPARADOS OU CONSERV.EM AGUA EDULCORADA,ETC.','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('20082090','ABACAXIS PREPARADOS OU CONSERV.DE OUT.MODO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29152200','ACETATO DE SODIO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29152300','ACETATO DE COBALTO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153100','ACETATO DE ETILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153200','ACETATO DE VINILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153300','ACETATO DE N-BUTILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153400','ACETATO DE ISOBUTILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153910','ACETATO DE LINALILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153931','ACETATO DE N-PROPILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153941','ACETATO DE DECILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153942','ACETATO DE HEXENILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153951','ACETATO DE BENZESTROL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153952','ACETATO DE DIENOESTROL','KG');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153953','ACETATO DE HEXESTROL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153954','ACETATO DE MESTILBOL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153955','ACETATO DE ESTILDESTROL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153961','ACETATO DE TRICLORO-ALFA-FENILETILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29153962','ACETATO DE TRICLOROMETILFENILCARBINILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29242911','ACETANILIDA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29242913','ACETAMINOFEN (PARACETAMOL)','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29309061','ACEFATO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29310041','ACETATO DE TRIFENILESTANHO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29329922','ABAMECTINA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29362112','ACETATO DE VITAMINA A1 ALCOOL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29362812','ACETATO DE D- OU DL-ALFA-TOCOFEROL,NAO MISTURADOS','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29379291','ACETATO DE ETINODIOL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('29379911','ACETATO DE CIPROTERONA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('34021210','ACETATO DE OLEILAMINA (AGENTE ORGANICO DE SUPERFICIE)','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('38069012','ABIETATOS DE METILA OU BENZILA,HIDROABIETATO DE METILA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('38089021','ACARICIDAS A BASE AMITRAZ,CLORFENVINFOS,METAMIDOFOS,ETC','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('38089022','ACARICIDAS A BASE CIEXATIN,OXIDO DE FEMBUTATIN,ETC.','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('39051200','ACETATO DE POLIVINILA,EM DISPERSAO AQUOSA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('39051910','ACETATO DE POLIVINILA,COM ALCOOL VINILICO,EM BLOCOS,ETC','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('39121110','ACETATO DE CELULOSE,NAO PLASTIFICADO,C/CARGA,FORMA PRIM','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('39121120','ACETATO DE CELULOSE,NAO PLASTIFICADO,S/CARGA,FORMA PRIM','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('39121200','ACETATO DE CELULOSE,PLASTIFICADO,EM FORMA PRIMARIA','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('48184090','ABSORVENTES E OUTS.ARTIGOS HIGIENICOS,DE PAPEL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('53052100','ABACA EM BRUTO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('53052900','ABACA TRABALHADO,NAO FIADO,ESTOPAS E DESPERDICIOS','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('56011000','ABSORVENTES E TAMPOES,ETC.DE PASTAS DE MATERIAS TEXTEIS','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('61121100','ABRIGOS PARA ESPORTES,DE MALHA DE ALGODAO','UN');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('61121200','ABRIGOS PARA ESPORTES,DE MALHA DE FIBRAS SINTETICAS','UN');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('61121900','ABRIGOS PARA ESPORTES,DE MALHA DE OUTS.MATERIAS TEXTEIS','UN');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('68051000','ABRASIVOS NAT/ARTIF.EM PO/GRAO,APLIC.EM TECIDO MAT.TEXT','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('68052000','ABRASIVOS NAT/ARTIF.EM PO/GRAO,APLIC.EM PAPEL/CARTAO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('68053010','ABRASIVOS NAT/ARTIF.C/SUPORTE PAPEL/CARTAO C/MAT.TEXTIL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('71171100','ABOTOADURAS E OUTS.BOTOES,DE METAIS COMUNS','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('73071100','ACESSORIOS MOLDADOS P/TUBOS DE FERRO FUND.N/MALEAVEL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('73071910','ACESSORIOS MOLDADOS P/TUBOS DE FERRO FUN.MALEAV.D>50MM','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('73071920','ACESSORIOS MOLDADOS P/TUBOS DE ACO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('73072300','ACESSORIOS PARA SOLDAR TOPO A TOPO,TUBOS DE ACOS INOX.','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('74121000','ACESSORIOS PARA TUBOS DE COBRE REFINADO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('74122000','ACESSORIOS PARA TUBOS DE LIGAS DE COBRE','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('75072000','ACESSORIOS PARA TUBOS DE NIQUEL','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('76090000','ACESSORIOS PARA TUBOS DE ALUMINIO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('8043000','ABACAXIS FRESCOS OU SECOS','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('8044000','ABACATES FRESCOS OU SECOS','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('84451924','ABRIDORAS DE FIBRAS DE LA','UN');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('84451925','ABRIDORAS DE OUTS.FIBRAS TEXTEIS VEGETAIS','UN');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('9102000','ACAFRAO','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('9103000','ACAFRAO-DA-TERRA (CURCUMA)','kg');
INSERT INTO NCM (CODE,DESCRIPTION,UNIT_MEASURE) VALUES ('94052000','ABAJURES DE CABECEIRA OU DE ESCRITORIO,ETC.ELETRICOS','kg');

Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('1.000','ENTRADAS OU AQUISIÇÔES DE SERVIÇOS DO ESTADO','Classificam-se, neste grupo, as Operações ou prestações em que o estabelecimento remetente esteja localizado na mesma unidade da Federação do destinatário',null);
Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('2.000','ENTRADAS OU AQUISIÇÔES DE SERVIÇOS DE OUTROS ESTADOS','Classificam-se, neste grupo, as Operações ou prestações em que o estabelecimento remetente esteja localizado em unidade da Federação diversa daquela do destinatário',null);
Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('3.000','ENTRADAS OU AQUISIÇÔES DE SERVIÇOS DO EXTERIOR','Classificam-se, neste grupo, as entradas de mercadorias oriundas de outro país, inclusive as decorrentes de aquisição por arrematação, concorrência ou qualquer outra forma de alienação promovida pelo poder público, e os serviços iniciados no exterior',null);
Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('5.000','SAÍDAS OU PRESTAÇÔES DE SERVIÇOS PARA O ESTADO','Classificam-se, neste grupo, as Operações ou prestações em que o estabelecimento remetente esteja localizado na mesma unidade da Federação do destinatário.',null);
Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('6.000','SAÍDAS OU PRESTAÇÔES DE SERVIÇOS PARA OUTROS ESTADOS','Classificam-se, neste grupo, as Operações ou prestações em que o estabelecimento remetente esteja localizado em unidade da Federação diversa daquela do destinatário',null);
Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('7.000','SAÍDAS OU PRESTAÇÔES DE SERVIÇOS PARA O EXTERIOR','Classificam-se, neste grupo, as Operações ou prestações em que o destinatário esteja localizado em outro país',null);
Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('1.100','COMPRAS PARA INDUSTRIALIZAÇÃO, PRODUÇÃO RURAL, COMERCIALIZAÇÃO OU PRESTAÇÃO DE SERVIÇOS','Dec. 28.868/2006 ¿ Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005','1.000');
Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('1.101','Compra para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)','Compra de mercadoria a ser utilizada em processo de industrialização ou produção rural, bem como a entrada de mercadoria em estabelecimento industrial ou produtor rural de cooperativa recebida de seus cooperados ou de estabelecimento de outra cooperativa.','1.100');
Insert into NATURE_OF_OPERATION (CFOP,LABEL,APPLICATION,PARENT) values ('1.102','Compra para comercialização','Classificam-se neste código as compras de mercadorias a serem comercializadas. Também serão classificadas neste código as entradas de mercadorias em estabelecimento comercial de cooperativa recebidas de seus cooperados ou de estabelecimento de outra cooperativa.','1.100');




insert into profile (ID,NAME,LABEL) values (1,'Administrador','Administrador do Sistema');

insert into PROFILE_ROLE (SELECT 1,key FROM role);

INSERT INTO USERS (ID,USERNAME,PASSWORD,EMPLOYEE,PROFILE,STATUS,BLOCKED) values (1,'ADMIN','brutal',null,1,'ACTIVE',0);

Insert into COMPANY (ID,DATE_REGISTER,TRADE_NAME,COMPANY_NAME,SOCIAL_SECURITY,REGISTER_NUMBER,CITY_REGISTER,ADDRESS,ADDRESS_NUMBER,ZIPCODE,PHONE,FAX,WEB_SITE,STATUS) 
values 
(1,to_date('13/12/2013','DD/MM/RRRR'),'Rochatec Technologies','Rochatec Technologies Ltda','12345678000190','331.253.162.233','13526',2,'1405','13091516','19982483311','19982483311','www.rochatec.com.br','ACTIVE');




