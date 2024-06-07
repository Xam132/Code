const vscode = require('vscode');
const axios = require('axios');
const JsonTreeView = require('./jsonTreeView');

function activate(context) {
  const jsonTreeView = new JsonTreeView(context);

  let disposable = vscode.commands.registerCommand('extension.viewJsonAsTree', async () => {
    try {
      const response = await axios.get('https://randomuser.me/api/');
      const jsonData = response.data;
      jsonTreeView.refresh(jsonData);
    } catch (error) {
      vscode.window.showErrorMessage('Failed to fetch JSON data');
    }
  });

  context.subscriptions.push(disposable);
}

function deactivate() {}

module.exports = {
  activate,
  deactivate
};
