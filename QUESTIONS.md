# Questions

- what applicable logistic functions there are to model a CRT?

- are all the 4 request fields mandatory, or sometimes it could receive a request with less fields?

# Issues encountered & workarounds

- REDIS is not fully supported for Windows, so I had to ran it in a docker container, throw docker toolbox (running on windows 10 home)

- At REST server restart, the data in REDIS is cleaned, thus need to reinitialize the key & fields. Not sure if it's a redis issue / docker redis container / some weird bug cleaning every key & field

# TODOs

- Log to file, and not just to console

- Initialize data in REDIS without running an additions REST request

- if not all the request fields are mandatory, change device model to take into account n fields
