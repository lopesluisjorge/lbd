create table if not exists emprestimo_video (
  id serial,
  emprestimo_id int not null,
  video_id      int not null,
  primary key (id),
  foreign key (emprestimo_id) references emprestimos(id),
  foreign key (video_id)      references videos(id));