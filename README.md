This is example for avoid saving entity without calling save method in repo.

  When Entity create by Jpa[save, findBy etc..] that entity will be store in persistenceContext, and if we modify that entity
then in after something we call the other save transaction system will also save first entity becasuse it in persistenceContext

for make more understant

User user = save(new User("rkritchat","1234")); // now a is in persistenceContext...
user.setPwd("5678"); 

Address tmp = new Address();
AddresRepo.save(tmp); 

for above code system should be save only tmp entity be casuse we using AddresRepo.save but acually
system also save User entity becasue User has change...

to avoid this case we need to use EntityManager.detach(Entity) for remove that entity from persistenceContext

and system will not save User entity anymore .... 

for check this one we can user EntityManager.contain and pass the entity to check the result.
before call that system will return true because it in state, after call the method system will 
return false and save notthing...


Good luck.

