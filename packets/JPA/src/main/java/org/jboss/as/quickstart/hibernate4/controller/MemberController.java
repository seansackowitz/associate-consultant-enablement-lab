/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstart.hibernate4.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.as.quickstart.hibernate4.model.Member;
import org.jboss.as.quickstart.hibernate4.model.MemberInfo;
import org.jboss.as.quickstart.hibernate4.service.MemberDao;

@Named
@Stateful
@ConversationScoped
public class MemberController {

    @Inject
    private Logger log;

    @Inject
    private FacesContext facesContext;

    @Inject
    private MemberDao memberDao;
    
    @Inject
    private Conversation conversation;

    private Member newMember;

    @Produces
    @Named
    public Member getNewMember() {
        return newMember;
    }

    public void register() {
        try {
            memberDao.create(newMember);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
            initNewMember();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, "Registration unsuccessful"));
        }
    }

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
    
    public String delete(Member member) {
    	Member managedMember = memberDao.getMember(member.getId());
    	memberDao.delete(managedMember);
    	
    	return null;
    }
    
    public String changeValue(Member member) {
    	member.setInfo(new MemberInfo());
    	member.getInfo().setDescription("This is a description");
    	memberDao.update(member);
    	
    	return null;
    }
    
    public String getInfo(Member member) {
    	if (member.getInfo() != null) {
    		Member managedMember = memberDao.getMember(member.getId());
    		log.info("Member information: " + managedMember.getInfo().getDescription());
    	} else {
    		log.log(Level.WARNING, "The user has no data!");
    	}

    	return null;
    }

    public void startConversation() {
    	if (conversation.isTransient()) {
    		conversation.begin();
    	}
    }
}
