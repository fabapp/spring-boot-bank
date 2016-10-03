import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import {Label, Panel, Modal, OverlayTrigger, Well, PageHeader, Grid, Row, Col, FormGroup, FormControl, Button} from 'react-bootstrap';
import 'whatwg-fetch';

class App extends Component {

	constructor(props) {
		super();
		this.state = {
			showModal: false,
			amount:0,
			exceptionMessage:'',
			account: {
				balance:0,
				accountNumber: {
					value: ""
				}
			}
		};
		this.login = this.login.bind(this);
		this.updateAccountNumber = this.updateAccountNumber.bind(this);
		this.withdrawal = this.withdrawal.bind(this);
		this.deposit = this.deposit.bind(this);
		this.book = this.book.bind(this);
		this.renderException = this.renderException.bind(this);
		this.amountChange = this.amountChange.bind(this);
		this.closeDialog = this.closeDialog.bind(this);
	}

	login(event) {
		let url = 'http://localhost:8085/rest/bank/accounts/' + this.state.account.accountNumber.value;
		fetch(url)
		.then(response => response.json())
		.then(data => this.checkStatus(data))
		.then(data => this.setState({account:data}))
		.catch(e => this.renderException(e));
	}

	checkStatus(response) {
		if ( ! response.exception ) {
			return response
		} else {
			if(response.exception){
				var error = new Error(response.message)
				error.response = response
				throw error
			} else {
				console.log('Exception', response);
			}
		}
	}

	updateAccountNumber(event) {
		this.setState({account:{accountNumber:{value:event.target.value}}});
	}

	withdrawal(e) {
		this.book('withdrawal', this.state.account.accountNumber.value, this.state.amount);
	}

	deposit(e) {
		this.book('deposit', this.state.account.accountNumber.value, this.state.amount);
	}

	book(action, accountNumber, amount) {
		let url = 'http://localhost:8085/rest/bank/accounts/'+action+'/' + accountNumber;
		console.log(url);
		console.log('strign', JSON.stringify({amount:amount}));
		fetch(url, {
			method: 'post',
			headers: {	'Accept':'application/json',
				'Content-Type':'application/json'},
				body: JSON.stringify({amount:amount})
			})
			.then(response => response.json())
			.then(data => this.checkStatus(data))
			.then(data => this.setState({amount:0, account:data}))
			.catch(e => this.renderException(e));
		}

		amountChange(e) {
			this.setState({ amount: e.target.value});
		}

		renderException(e) {
			this.setState({amount:0});
			this.setState({showModal:true, exceptionMessage:e.message});
		}

		closeDialog() {
			this.setState({showModal:false, exceptionMessage:''});
		}

		render() {
			return (
				<div className="App">
					<Panel>
					<PageHeader>
						<h1>The ATM</h1>
						<p><small>This ATM allows deposit and withdrawal to bank accounts. First enter an account number and press login. When the balance gets displayed you can do transactions.</small></p>
					</PageHeader>
					<Panel>
						<AtmLogin loginCallback={this.login} accountNumberChange={this.updateAccountNumber} account={this.state.account}/>
					</Panel>
					<Panel>
						<AtmBookingForm account={this.state.account} withdrawal={this.withdrawal} deposit={this.deposit} amountChange={this.amountChange} amount={this.state.amount}/>
					</Panel>
					<Modal show={this.state.showModal} onHide={this.close}>
						<Modal.Header closeButton>
							<Modal.Title>An Error Occured</Modal.Title>
						</Modal.Header>
						<Modal.Body>
							<p>{this.state.exceptionMessage}</p>
						</Modal.Body>
						<Modal.Footer>
							<Button onClick={this.closeDialog}>Close</Button>
						</Modal.Footer>
					</Modal>
					</Panel>
				</div>

			);
		}
	}

	class AtmLogin extends Component {
		render() {
			return(
				<Grid>
					<Row>
						<Col md={4}><Label>Account number:</Label></Col>
						<Col md={8}>
							<FormControl
								type="text"
								ref="accountNumberInput"
								value={this.props.account.accountNumber.value}
								onChange={this.props.accountNumberChange}
								placeholder="Account number"/>
						</Col>
					</Row>
					<Row>
						<Col md={4}></Col>
						<Col md={8}><Button onClick={this.props.loginCallback}>LOGIN</Button></Col>
					</Row>
				</Grid>
			)
		}
	}

	class AtmBookingForm extends Component {
		render(){
			return(
				<Grid>
					{this.props.account ?
						<Row ref="balanceDisplay">
							<Col md={4}>
								<Label>Your Balance: </Label>
							</Col>
							<Col md={8}><Well>{this.props.account.balance}</Well></Col>
						</Row>
						: ""
					}
					<Row>
						<Col md={4}>
							<Label>Transfer amount:</Label>
						</Col>
						<Col md={8}>
							<FormControl type="text" value={this.props.amount} onChange={this.props.amountChange} placeholder="amount" />
						</Col>
					</Row>
					<Row>
						<Col md={4}></Col>
						<Col md={8}>
							<Button onClick={this.props.withdrawal}>withdrawal</Button>
							<Button onClick={this.props.deposit}>deposit</Button>
						</Col>
					</Row>
				</Grid>
			)
		}
	}

	export default App;
