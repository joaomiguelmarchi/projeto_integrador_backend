package org.pi.utils.functions

import org.pi.utils.entities.DefaultError

val UNEXPECTED = DefaultError("UNEXPECTED", "unexpected")
val INVALID_CREDENTIALS = DefaultError("INVALID_CREDENTIALS", "Credenciais invalidas")
val AUTH_EMAIL_IS_EMPTY = DefaultError("AUTH_EMAIL_IS_EMPTY", "Email deve ser informado")
val AUTH_NAME_IS_EMPTY = DefaultError("AUTH_NAME_IS_EMPTY", "Nome deve ser informado")
val AUTH_PASSWORD_IS_EMPTY = DefaultError("AUTH_PASSWORD_IS_EMPTY", "Senha incorreta")