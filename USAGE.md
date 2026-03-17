# APK-SFTP User Guide

## Getting Started

### 1. Import SSH Key
1. Open the app and go to **SSH Keys** (🔑)
2. Tap the **+** button to import a key
3. Select your SSH private key file from device storage
4. Confirm import - app will display key fingerprint & algorithm

### 2. Create Connection Profile
1. Go to **Saved Profiles** (📁)
2. Tap **+** to create new profile
3. Enter:
   - **Profile Name**: e.g., "My Server"
   - **Hostname/IP**: Server address
   - **Port**: Default 22
   - **Username**: SSH username
   - **Key ID**: Select imported SSH key
4. Tap **Create**

### 3. Connect & Transfer Files
1. From **Home** screen, tap connection status or profile
2. App attempts SSH connection
3. Once connected (🟢 status), tap **File Transfer**
4. **Local tab**: Browse your device files
5. **Remote tab**: Browse server files
6. Tap **⬇** (download) or **⬆** (upload) to transfer
7. Monitor progress in real-time

## Features

### Connection Management
- **Quick Connect**: Tap profile to connect immediately
- **Connection Status**: Green = connected, Red = disconnected
- **Auto-Reconnect**: Settings → Auto Reconnect (auto-retry on disconnect)
- **Disconnect**: Home → Disconnect button

### File Operations
- **Browse**: Navigate directories (local/remote)
- **Upload**: Download from device to server
- **Download**: Download from server to device
- **Delete**: Remote file deletion (trash icon)
- **Rename**: Remote file renaming
- **Progress**: Real-time transfer speed & percentage
- **Cancel**: Stop transfer in progress

### Key Management
- **Import**: File picker to import SSH keys
- **View Details**: Key algorithm, size, fingerprint
- **Delete**: Remove imported keys
- **Passphrase**: Supports password-protected keys

### Profiles
- **Quick Access**: Save frequently-used connections
- **Favorites**: Mark profiles as favorites
- **Reorder**: Change profile order
- **Export**: Backup profiles (JSON, without keys)
- **Import**: Restore profiles from backup

### Settings
- **Auto Reconnect**: Toggle automatic reconnection
- **Verify Host Key**: SSH host key verification
- **Timeout**: Connection timeout in seconds
- **Dark Mode**: UI theme preference

## Troubleshooting

### "Connection Failed"
- Check hostname/port are correct
- Verify SSH key has proper permissions
- Ensure network connectivity
- Check firewall rules

### "Authentication Failed"
- Verify SSH key is correct
- Check username is correct
- If key has passphrase, enter it in Connection screen
- Check server allows public key authentication

### "Permission Denied on File"
- Check user has read/write access on server
- Verify file/folder permissions
- Try as different user if available

### "Transfer Failed"
- Check available disk space
- Verify network stability
- Check server disk space (for uploads)
- Try smaller file first

### "Key Import Failed"
- Verify file is valid SSH private key
- Check file permissions
- Ensure key format is supported (RSA, ECDSA, Ed25519)

## Best Practices

1. **Security**
   - Use strong passphrases for SSH keys
   - Keep keys on secure device storage
   - Enable "Verify Host Key" for known servers
   - Review app permissions periodically

2. **Performance**
   - Close unused connections (Disconnect button)
   - Avoid very large files over mobile networks
   - Use profiles for frequently-accessed servers
   - Monitor transfer speed and cancel if too slow

3. **Reliability**
   - Keep app updated
   - Use WiFi for large transfers
   - Enable auto-reconnect for unstable networks
   - Regular backups of important profiles

## Keyboard Shortcuts
- **Back**: Navigate to previous screen
- (UI-based navigation for other functions)

## Permissions Required
- **INTERNET**: SSH/SFTP connections
- **READ_EXTERNAL_STORAGE**: File uploads
- **WRITE_EXTERNAL_STORAGE**: File downloads
- **ACCESS_NETWORK_STATE**: Connection detection

## File Size Limits
- No theoretical limit for file transfers
- Practical limit depends on:
  - Available device storage
  - Server storage
  - Network stability
  - Available RAM (for progress tracking)

## Support & Feedback
For issues or feature requests, check the GitHub repository:
https://github.com/monghithub/apk-sftp
