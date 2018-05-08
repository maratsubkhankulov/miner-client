# Intro

This android application is designed to run the [block hashing algorithm](https://en.bitcoin.it/wiki/Block_hashing_algorithm)
as part of a distributed mining swarm. It works with this [server implementation](https://github.com/maratsubkhankulov/miner-server)
which generates jobs for the client. The client runs the hashcash function using a range of nonces and if successful submits the solution
before asking for more work.

[This page](https://en.bitcoin.it/wiki/Block_hashing_algorithm) was a useful reference for raw header layout.

_This is a work in progress and may never be completed_

# To do
- [ ] Implement BlockHeader.toByteArray()
- [ ] Implement SolverTest
- [ ] Test client-server integration and make it work
- [ ] Improve performance by hashing in native code and using multiple threads
