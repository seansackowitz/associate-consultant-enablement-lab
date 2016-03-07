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
package org.jboss.as.quickstart.hibernate4.service;

import org.jboss.as.quickstart.hibernate4.model.Member;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Stateless
public class MemberDao {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Member> memberEventSrc;

    public void create(Member member) throws Exception {
        log.info("Registering " + member.getName());

        em.persist(member);
        memberEventSrc.fire(member);
    }
    
    public void delete(Member member) {
    	log.info("Deleting member with id " +member.getId());

        em.remove(member);
        memberEventSrc.fire(member);
    }
    
    public void update(Member member) {
    	log.info("Updating member with id " + member.getId());

        em.merge(member);
        memberEventSrc.fire(member);
    }
    
    public Member getMember(long id) {
    	log.info("Getting member with id " + id);

        return em.find(Member.class, id);
    }
}
