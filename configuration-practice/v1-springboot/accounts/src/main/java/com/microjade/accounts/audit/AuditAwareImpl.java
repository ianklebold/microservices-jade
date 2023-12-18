package com.microjade.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Este componente gestiona los campos de auditoria
 */
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //Devuelve quien es el actual auditor
        //Aqui se pone quien es el que esta actualmente en sesion
        return Optional.of("ACCOUNTS_MS");
    }
}
