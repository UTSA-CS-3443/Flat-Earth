# inefficient probably. should compile the regex and structure this better but nah
# i couldve been done in way less code and way cleaner
# program more or less expects java programming standards
# assumes 1 class per file
# meant to have filenames piped into, something like this:
#	find FileSystem -name "*.java" | python3 umlscaper.py
# generates some copy and paste stuff for uml, no xml

import re
import sys


# gets rid of all imports and stops at the class declaration. Returns 
# the class name. in reality, get's rid of everything above the first
# class declaration

def kill_imports_get_class(f):
	break_now = False # read below
	m = None
	for line in f:
		if break_now:
			break
		if is_comment_and_kill(f, line):
			continue
		m = re.match(".*(class|enum|interface)\s([^\s]*).*", line)
		if m:
			# lazy way to consume the next line if the { was placed
			# on the next line instead of this one
			if not re.match(".*{.*", line):
				break_now = True
			else:
				break
	return m.group(2)


# will get all variables of that class. have to be under the 
# class declaration and above the first method 

def scrape_fields(f, fields):
	for line in f:
		if is_blank_line(line):
			continue
		m = re.match("[^=]*\s+([^=]*)\s+([^=]*)(;|\s=).*", line)
		if not m: 
			if not is_comment_and_kill(f, line):
				# if it's not a field and not a comment, then it's a function
				return line
			continue
		fields.append(m.group(2) + ": " + m.group(1))


# gets all the functions and constructors. Should work with abstract function
# too, not too sure

def functions_and_constructors(f, extra_line, functions, constructors):
	# parse the extra line first
	if not is_comment_and_kill(f, extra_line):
		m = re.match("\s*(public|protected|private)\s+([^\s\(]*)\s*\(.*", extra_line)
		if m: # if it's a constructor
			con = "<<constructor>>("+parse_parameters(extra_line)+")"
			kill_function(f, extra_line)
			constructors.append(con)
		else:
			func = ""
			m = re.match(".*\s([^\s]*)\s+([^\s]*)\s*\(.*", extra_line)
			if m: # if it's a function
				func +=m.group(2)+"("+parse_parameters(extra_line)+"): "+m.group(1)
				kill_function(f, extra_line)
				functions.append(func)
	# same as above but now in the loop
	for line in f: 
		if is_comment_and_kill(f, line):
			continue
		m = re.match("\s*(public|protected|private)\s+([^\s\(]*)\s*\(.*", line)
		if m:
			con = "<<constructor>>"+m.group(2)+"("+parse_parameters(extra_line)+")"
			constructors.append(con)
			kill_function(f, line)
			continue
		func = ""
		m = re.match(".*\s([^\s]*)\s+([^\s]*)\s*\(.*", line) # match func header
		if m:
			func += m.group(2)+"("+parse_parameters(line)+"): "+m.group(1)
			kill_function(f, line)
			functions.append(func)
	
			
# parses parameters for the function or constructor
						
def parse_parameters(line):
	params = line[line.index("(")+1:line.index(")")] # get just the parameters
	if params == "": # if there is none return
		return ""
	params = params.split(',')
	returner = ""
	first = True
	for param in params: # for each one, get just the type
		if not first:
			returner += ", "

		param = param.lstrip().split(' ')
		
		returner += param[0]
		first = False
	return returner


# called after the function header is read, trashes the rest of the function
# uses a stack to keep track fo brackets

def kill_function(f, l):
	# if it's a one line function or abstract
	if re.match(".*}.*", l) or re.match(".*;", l):
		return
	stack = 0
	# if the bracket is on this line, ncrement stack
	if re.match(".*{.*", l):
		stack += 1
	for line in f:
		if re.match(".*{.*", line):
			stack += 1
		if re.match(".*}.*", line):
			stack -= 1
			if stack == 0:
				return


# returns true if it's a comment or an empty line. Trashes both

def is_comment_and_kill(f, l):
	if is_blank_line(l):
		return True
	if not re.match("\s*/.*", l): # if it's not a comment, return false
		return False
	if re.match("\s*//.*", l): # if it's a single line comment, return true
		return True
	if re.match(".*\*/.*", l):
			return True
	for line in f: # kill the multi line comment
		if re.match(".*\*/.*", line):
			return True
	print("Should never get here:'" + l+"'")
	sys.exit(1)

# checks if blank line

def is_blank_line(line):
	if re.match(".*@.*", line):
		return True
	return line.rstrip() == ""


# loop through standard in

for filename in sys.stdin:
	stripped = filename.replace('\n','')
	functions = []
	constructors = []
	fields = []
	class_name = ""
	with open(stripped) as f:
		#print("File: "+stripped)
		#print("kill imports:")
		class_name = kill_imports_get_class(f) # get rid of imports and get class name
		#print("scrape fields:")
		extra_line = scrape_fields(f, fields) # get the variable declarations
		#print("scrape functions:")
		functions_and_constructors(f, extra_line, functions, constructors) # funcs
	
	# write it to the output here
	print("_____________________________________________________________________________________")
	print(class_name)
	print("------------------")
	for field in fields:
		print(field)
	print("------------------")
	for con in constructors:
		print(con)
	for fun in functions:
		print(fun)
	print("_____________________________________________________________________________________")







