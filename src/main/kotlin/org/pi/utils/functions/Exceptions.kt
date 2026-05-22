package org.pi.utils.functions

import org.pi.utils.entities.DefaultError

val UNEXPECTED = DefaultError("UNEXPECTED", "unexpected")
val INVALID_CREDENTIALS = DefaultError("INVALID_CREDENTIALS", "Credenciais invalidas")
val AUTH_EMAIL_IS_EMPTY = DefaultError("AUTH_EMAIL_IS_EMPTY", "Email deve ser informado")
val AUTH_NAME_IS_EMPTY = DefaultError("AUTH_NAME_IS_EMPTY", "Nome deve ser informado")
val AUTH_PASSWORD_IS_EMPTY = DefaultError("AUTH_PASSWORD_IS_EMPTY", "Senha incorreta")
val NAME_IS_EMPTY = DefaultError("NAME_IS_EMPTY", "Nome invalido")
val EMAIL_IS_EMPTY = DefaultError("EMAIL_IS_EMPTY", "Email invalido")
val BIRTHDAY_IS_EMPTY = DefaultError("BIRTHDAY_IS_EMPTY", "Data de nascimento invalida")
val AGE_IS_EMPTY = DefaultError("AGE_IS_EMPTY", "Idade invalida")
val SEX_IS_EMPTY = DefaultError("SEX_IS_EMPTY", "Sexo invalido")
val RESPONSIBLE_IS_EMPTY = DefaultError("RESPONSIBLE_IS_EMPTY", "Responsavel invalido")
val DOCUMENT_IS_EMPTY = DefaultError("DOCUMENT_IS_EMPTY", "Documento invalido")
val ADDRESS_IS_EMPTY = DefaultError("ADDRESS_IS_EMPTY", "Endereco invalido")
val ADDRESSES_NUMBER_IS_EMPTY = DefaultError("ADDRESSES_NUMBER_IS_EMPTY", "Numero do endereco invalido")
val HOME_PHONE_NUMBER_IS_EMPTY = DefaultError("HOME_PHONE_NUMBER_IS_EMPTY", "Telefone residencial invalido")
val COMMERCIAL_PHONE_NUMBER_IS_EMPTY = DefaultError("COMMERCIAL_PHONE_NUMBER_IS_EMPTY", "Telefone comercial invalido")
val PHONE_NUMBER_IS_EMPTY = DefaultError("PHONE_NUMBER_IS_EMPTY", "Telefone invalido")
val OCCUPATION_IS_EMPTY = DefaultError("OCCUPATION_IS_EMPTY", "Ocupacao invalida")