package id.ac.tazkia.payment.cimb.command;

import id.ac.tazkia.payment.cimb.dto.VaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class VaHandlerFactory {

    @Autowired private Set<VaHandler> commands;
    private VaHandler defaultCommand = new UnsupportedVaHandler();

    public VaHandler getHandler(VaStatus status){
        for(VaHandler c : commands) {
            if(c.supports(status)) return c;
        }

        return defaultCommand;
    }
}
