# WAES Assignment Scalable Web 


[![Build Status](https://travis-ci.org/rsouza01/waes-scalable-web.svg?branch=master)](https://travis-ci.org/rsouza01/waes-scalable-web)  [![Codacy Badge](https://api.codacy.com/project/badge/Grade/d100b2c84f834615a0679e6e636817cd)](https://www.codacy.com/app/rsouza01/waes-scalable-web?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=rsouza01/waes-scalable-web&amp;utm_campaign=Badge_Grade)  [![codecov](https://codecov.io/gh/rsouza01/waes-scalable-web/branch/master/graph/badge.svg)](https://codecov.io/gh/rsouza01/waes-scalable-web)  [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## GOAL

The goal of this assignment is to show your coding skills and what you value in software engineering. We value new ideas so next to the original requirement feel free to improve/add/extend.
We evaluate the assignment depending on your role (Developer/Tester) and your level of seniority

## The assignment

- Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
	- <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
- The provided data needs to be diff-ed and the results shall be available on a third end point
	- <host>/v1/diff/<ID>
- The results shall provide the following info in JSON format
	- If equal return that
	- If not of equal size just return that
	- If of same size provide insight in where the diffs are, actual diffs are not needed (So mainly offsets + length in the data)
- Make assumptions in the implementation explicit, choices are good but need to be communicated

## Must haves
- Solution written in Java
- Internal logic shall be under unit test
- Functionality shall be under integration test
- Documentation in code
- Clear and to the point readme on usage

## Nice to haves
- Suggestions for improvement

Please	upload	the	assignment	on	your	personal	GitHub	account	once	finished,	and	send	the	link	to	the	
responsible	Tech Sourcer before	deadline.

## Development and Usage

See my comments on the implementation [here](docs/COMMENTS.md) and my documentation [here](docs/USAGE.md).

## Author

**Rodrigo Alvares de Souza** - [rsouza01@gmail.com](rsouza01@gmail.com)