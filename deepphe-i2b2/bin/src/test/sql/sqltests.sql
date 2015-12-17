select count(*) from observation_fact;
select count(*) from observation_fact where sourcesystem_cd = 'DEEPPHE2';
select patient_num from patient_dimension where sourcesystem_cd = 'DEEPPHE2';
select encounter_num from visit_dimension where patient_num = 0 and sourcesystem_cd = 'DEEPPHE2';
select concept_cd from concept_dimension where sourcesystem_cd = 'DEEPPHE2';

delete from observation_fact where sourcesystem_cd like 'DEEPPHE%';
delete from visit_dimension where sourcesystem_cd like 'DEEPPHE%';
delete from concept_dimension where sourcesystem_cd like 'DEEPPHE%';
delete from patient_dimension where sourcesystem_cd like 'DEEPPHE%';